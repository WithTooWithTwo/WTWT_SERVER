package cupitoo.wtwt.controller;

import cupitoo.wtwt.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageCnntroller {
    private final FileStore fileStore;

    @ResponseBody
    @GetMapping("/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
