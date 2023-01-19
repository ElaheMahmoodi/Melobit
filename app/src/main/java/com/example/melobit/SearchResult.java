package com.example.melobit;

import java.util.List;

public class SearchResult {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public class Result{
     private String type;

        public String getType() {
            return type;
        }

        public Artist getArtist() {
            return artist;
        }

        public Song getSong() {
            return song;
        }

        //     artist
     private Artist artist;
     private Song song;
//     song
        public class Artist{
            String fullName;
            private Image image;

    public Image getImage() {
        return image;
    }

    public String getFullName() {
                return fullName;
            }

        }


        public class Song{
            private String title;
            private Image image;

            public Image getImage() {
                return image;
            }

            public String getTitle() {
                return title;
            }

        }

        public class Image {

            private Thumbnail thumbnail;

            public Thumbnail getThumbnail() {
                return thumbnail;
            }

            public class Thumbnail {
                private String url;

                public String getUrl() {
                    return url;
                }
            }
        }

    }
}
