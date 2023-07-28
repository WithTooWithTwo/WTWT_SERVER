package cupitoo.wtwt.service;

import cupitoo.wtwt.controller.post.CreatePostReq;
import cupitoo.wtwt.dto.PostDetails;
import cupitoo.wtwt.dto.PostListElement;
import cupitoo.wtwt.dto.UserProfile;
import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.Image;
import cupitoo.wtwt.model.group.*;
import cupitoo.wtwt.model.user.Gender;
import cupitoo.wtwt.model.user.User;
import cupitoo.wtwt.model.post.Post;
import cupitoo.wtwt.model.post.PostImage;
import cupitoo.wtwt.repository.*;
import cupitoo.wtwt.repository.group.*;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final GroupUserRepository groupUserRepository;
    private final FileStore fileStore;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(Long userId, CreatePostReq request) {
        User user = userRepository.findById(userId).get();

        Preference preference = Preference.builder()
                .maxAge(request.getPreferMaxAge().orElse(null))
                .headCount(request.getPreferHeadCount().orElse(null))
                .gender(request.getPreferGender().orElse(Gender.NONE))
                .minAge(request.getPreferMinAge().orElse(null))
                .build();

        Post post = new Post(request.getTitle(), request.getContent(), user);
        postRepository.save(post);

        request.getImages().ifPresent(images -> {
            try {
                List<Image> imageList = fileStore.storeFiles(images);
                for (Image i: imageList) {
                    postImageRepository.save(new PostImage(post, i));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Category category = categoryRepository.findById(request.getCategory_id()).get();

        Group group = Group.builder()
                .groupName(request.getTitle())
                .firstDay(LocalDate.parse(request.getFirstDay(), DateTimeFormatter.ISO_DATE))
                .lastDay(LocalDate.parse(request.getLastDay(), DateTimeFormatter.ISO_DATE))
                .lightning(request.getLightning().orElse(Boolean.FALSE))
                .status(RecruitStatus.OPEN)
                .category(category)
                .preference(preference)
                .leader(user)
                .build();
        groupRepository.save(group);

        List<PostImage> postImages = postImageRepository.findByPost(post);
        if(postImages.size() != 0) {
            group.changeGroupImage(postImages.get(0).getImage());
        }

        for (String id : request.getMembers().orElse(new ArrayList<>())) {
            GroupUser groupUser = new GroupUser(group, userRepository.findById(Long.parseLong(id)).get());
            groupUserRepository.save(groupUser);
        }

        post.setGroup(group);

        return group.getId();
    }

    /**
     * 단일 게시글 조회
     */
    @Transactional
    public PostDetails findPostWithGroup(Long postId) {
        Post post = postRepository.findById(postId).get();
        post.getImages(); //강제 초기화
        post.getCreatedBy().getProfileImage(); //강제 초기화
        post.updateHit();

        Group group = groupRepository.findByPost(post);
        List<User> members = group.getMembers().stream()
                .map(m -> m.getUser())
                .collect(Collectors.toList());

        PostDetails result = new PostDetails(post, group, members);
        return result;
    }

    /**
     * 게시글 검색 (리스트 조회)
     */
    public List<PostListElement> findAllWithFilter(PostSearch postSearch) {
        List<Post> posts = postRepository.findAllWithFilter(postSearch);
        List<PostListElement> result = new ArrayList<>();

        for (Post p : posts) {
            Group group = groupRepository.findByPost(p);
            PostListElement e = PostListElement.builder()
                            .id(p.getId())
                            .writer(new UserProfile(p.getCreatedBy()))
                            .createdAt(p.getCreatedAt())
                            .title(p.getTitle())
                            .content(p.getText())
                            .isLightning(group.isLightning())
                            .status(group.getStatus())
                            .preferHeadCount(group.getPreference().getHeadCount())
                            .headCount(group.getMembers().size())
                            .hits(p.getHits())
                    .build();

            result.add(e);
        }

        return result;
    }

}
