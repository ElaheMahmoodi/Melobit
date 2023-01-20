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
            private Audio audio;
            private String downloadCount;
            private String releaseDate;

            public String getDownloadCount() {
                return downloadCount;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            private com.example.melobit.Song.Result.Album album;
            public Audio getAudio() {
                return audio;
            }
            public com.example.melobit.Song.Result.Album getAlbum() {
                return album;
            }

            public  class  Album{
                private List<com.example.melobit.Song.Result.Artists> artists;

                public List<com.example.melobit.Song.Result.Artists> getArtists() {
                    return artists;
                }
            }

            public class Audio{
                private Medium medium;

                public Medium getMedium() {
                    return medium;
                }

                public class Medium{
                    String url ;

                    public String getUrl() {
                        return url;
                    }
                }
            }

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
