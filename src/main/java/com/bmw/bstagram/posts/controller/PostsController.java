package com.bmw.bstagram.posts.controller;

import com.bmw.bstagram.posts.dto.PostsDto;
import com.bmw.bstagram.posts.dto.PostsResponseDto;
import com.bmw.bstagram.posts.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "posts", description = "게시물 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @Operation(summary = "get posts", description = "지역에 대한 posts들 가져오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostsResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "province", description = "시", example = "경기도"),
            @Parameter(name = "city", description = "도", example = "고양시"),
            @Parameter(name = "hashtag", description = "검색한 해시태그", example = "['#자장면', '#중국집']")
    })
    @ResponseBody
    @GetMapping( "")
    public PostsResponseDto getPosts(
            @RequestParam(value = "province") String province,
            @RequestParam(value = "city") String city,
            @RequestParam(value = "hashtag", required = false) @Nullable String hashtag
    ) {
        System.out.println(province);
        System.out.println(city);
        System.out.println(hashtag);
        return new PostsResponseDto(1);
    }

    @PostMapping("")
    public long savePosts(@RequestBody PostsDto.Info registryDto) {
        return postsService.savePosts(registryDto);
    }
}
