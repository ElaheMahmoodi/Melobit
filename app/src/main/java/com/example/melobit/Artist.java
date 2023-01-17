package com.example.melobit;

import java.util.List;

public class Artist {
    private String total ;
    private List<Result> results;

    public String getTotal() {
        return total;
    }

    public List<Result> getResults() {
        return  results;
    }

    public class Result {
        private String id;
        private String sumSongsDownloadsCount;
        private String followersCount;
        private String fullName;
        private Image image;

        public String getFollowersCount() {
            return followersCount;
        }

        public String getSumSongsDownloadsCount() {
            return sumSongsDownloadsCount;
        }

        public String getFullName() {
            return fullName;
        }

        public class Image {
            private Slider slider ;
            private Thumbnail thumbnail;
            public class Thumbnail{
                private String url;

                public String getUrl() {
                    return url;
                }
            }

            public Thumbnail getThumbnail() {
                return thumbnail;
            }
        }
        public  class Slider{
            private String url;
        }

        public String getId() {
            return id;
        }

        public Image getImage() {
            return image;
        }
    }
}