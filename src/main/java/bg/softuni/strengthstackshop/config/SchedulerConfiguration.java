package bg.softuni.strengthstackshop.config;

import bg.softuni.strengthstackshop.service.CommentService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    private final CommentService commentService;

    public SchedulerConfiguration(CommentService commentService) {
        this.commentService = commentService;
    }

    @Scheduled(cron = "1 1 1 * * *")
    public void clearOldProduct() {
        commentService.clearOldComments();
    }
}
