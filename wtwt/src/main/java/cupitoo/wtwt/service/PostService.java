package cupitoo.wtwt.service;

import cupitoo.wtwt.controller.post.CreatePostReq;
import cupitoo.wtwt.model.Category;
import cupitoo.wtwt.model.Group.*;
import cupitoo.wtwt.model.User.Gender;
import cupitoo.wtwt.model.User.User;
import cupitoo.wtwt.repository.CategoryRepository;
import cupitoo.wtwt.repository.GroupRepository;
import cupitoo.wtwt.repository.PostRepository;
import cupitoo.wtwt.repository.UserRepository;
import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FileStore fileStore;

    /**
     * 게시글 생성
     */
    @Transactional
    public Long savePostWithGroup(Long userId, CreatePostReq request) {
        User user = userRepository.findById(userId).get();

        Preference preference = Preference.builder()
                .maxAge(request.getPreferMaxAge())
                .headCount(request.getPreferHeadCount())
                .gender(request.getPreferGender())
                .minAge(request.getPreferMinAge())
                .build();

        Post post = new Post(request.getTitle(), request.getContent(), user);
        postRepository.save(post);

        Category category = categoryRepository.findById(request.getCategory_id()).get();

        List<GroupUser> members = new ArrayList<>();
        members.add(new GroupUser());

        for(String email : request.getCompanions()) {
            companions.add(new GroupUser(userService.findOne(email)));
        }

        Group.GroupBuilder groupBuilder = Group.builder()
                .groupName(request.getTitle())
                .firstDay(request.getFirstDay())
                .lastDay(request.getLastDay())
                .lightning(request.isLightning())
                .status(RecruitStatus.OPEN)
                .post(post)
                .category(category)
                .preference(preference)
                .leader(user)
                .members();



        return postRepository.save(post).getId();
    }



}
