package com.example.melobit;

import java.util.List;

public class Song {
    private String total ;
    private List<Result> results;

    public String getTotal() {
        return total;
    }

    public List<Result> getResults() {
        return  results;
    }

    public class Result {
        private  String id;
        private String downloadCount;
        private String title;
        private Image image;
        private Album album;

        public Album getAlbum() {
            return album;
        }

        public  class  Album{
           private List<Artists> artists;

            public List<Artists> getArtists() {
                return artists;
            }
        }
        public  class Artists {
            private String fullName;

            public String getFullName() {
                return fullName;
            }
        }
        public class Image {
            private Slider slider ;
        }
        public  class Slider{
            private String url;
        }

        public String getId() {
            return id;
        }

        public String getDownloadCount() {
            return downloadCount;
        }

        public String getTitle() {
            return title;
        }

        public Image getImage() {
            return image;
        }
    }
}