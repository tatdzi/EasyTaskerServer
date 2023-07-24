package by.taskManager.user_service.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class AppProperty {
    private GenreProd genre;

    public GenreProd getGenre() {
        return genre;
    }

    public void setGenre(GenreProd genre) {
        this.genre = genre;
    }
    public static class GenreProd{
        private int maxCount;

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }
    }
}
