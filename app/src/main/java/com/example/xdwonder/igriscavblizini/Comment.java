package com.example.xdwonder.igriscavblizini;

/**
 * Created by xdwonder on 4.3.2017.
 */

public class Comment {
   private String idComment;
   private String idUser;
   private String idPlayground;
   private String content;
   private int rating;//1,2,3,4,5


    public Comment(String idComment, String idUser, String idPlayground, String content, int rating) {
        this.idComment = idComment;
        this.idUser = idUser;
        this.idPlayground = idPlayground;
        this.content = content;
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "idComment='" + idComment + '\'' +
                ", idUser='" + idUser + '\'' +
                ", idPlayground='" + idPlayground + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
