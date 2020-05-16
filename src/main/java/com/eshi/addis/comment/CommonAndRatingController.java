package com.eshi.addis.comment;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommonAndRatingController {
    private CommentAndRatingService commentAndRatingService;

    public CommonAndRatingController(CommentAndRatingService commentAndRatingService) {
        this.commentAndRatingService = commentAndRatingService;
    }

    @PostMapping("/laundry/{laundryId}/user/{userId}")
    public CommentAndRating create(@PathVariable("userId") long userId, @PathVariable("laundryId") long laundryId, @RequestBody CommentAndRating commentAndRating) {
        return commentAndRatingService.create(laundryId, userId, commentAndRating);
    }

    @PutMapping("/{id}")
    public CommentAndRating update(@PathVariable long id, @RequestBody CommentAndRating commentAndRating) {
        return commentAndRatingService.update(id, commentAndRating);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        return commentAndRatingService.delete(id);
    }

}
