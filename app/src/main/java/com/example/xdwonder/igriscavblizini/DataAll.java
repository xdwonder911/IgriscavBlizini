package com.example.xdwonder.igriscavblizini;

import android.provider.ContactsContract;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;

import static com.example.xdwonder.igriscavblizini.ApplicationMy.da;

/**
 * Created by xdwonder on 4.3.2017.
 */

public class DataAll {
    public static final String Igrisce_ID="igrisce_idXX";//ZAKA DAFAQ MAMO IGRISCE ID V DATAALL IN NE V IGRISCE classu
    private User userMe;
    private TagList tags;
    //private ArrayList<Playground> playgroundsList;
    private ArrayList<Playground> playgroundsList;
    private ArrayList<Comment> commentsList;
   // public static SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    public static SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");

    public DataAll(){
        userMe = new User("0","unknowsn","unkonown@unknown.com");
        tags= new TagList();
        //playgroundsList = new ArrayList<>();
        playgroundsList = new ArrayList<>();
        commentsList=new ArrayList<>();


    }

    public Playground addPlayground(long x, long y) {
        Playground tmp=new Playground(new ArrayList<String>(),new ArrayList<Tag>(),System.currentTimeMillis(),userMe.getIdUser(),x,y);
        playgroundsList.add(tmp);
        return tmp;
    }

    public void addPlaygroundtag(Playground x,Tag y){
x.getTagList().add(y);
    }

    public static DataAll scenarijA(){
        DataAll da=new DataAll();
        da.userMe = new User("1","xdwonder","ziga.aman@gmail.com");
        Playground tmp = da.addPlayground(14,112);
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(0));
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(2));
        //tmp.addImgPath("/storage/emulated/0/Android/data/com.example.xdwonder.igriscavblizini/files/Pictures/157641755.jpg");
        da.getPlaygroundsList_prvi().setRating((float)4.2);//zalasno, drgač se bo zračuno oiz commentov

        tmp = da.addPlayground(10,1225);
        da.getPlaygroundsList().get(1).setRating((float)2);
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(0));
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(1));
        //tmp.addImgPath("/storage/emulated/0/Android/data/com.example.xdwonder.igriscavblizini/files/Pictures/157641755.jpg");
        tmp = da.addPlayground(10,87);
       // da.getPlaygroundsList().get(1).setRating((float)1);
        tmp.setRating((float)1);
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(2));

        tmp = da.addPlayground(28,0);
       // da.getPlaygroundsList().get(1).setRating((float)5);
        tmp.setRating((float)4);
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(3));

        tmp = da.addPlayground(442,352);
        //da.getPlaygroundsList().get(1).setRating((float)3);
        tmp.setRating((float)3);
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(4));

        tmp = da.addPlayground(11,1);
        //da.getPlaygroundsList().get(1).setRating((float)3.5);
        tmp.setRating((float)3.5);
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(3));
        da.addPlaygroundtag(tmp,da.tags.getTagList().get(0));



        System.out.println(da.playgroundsList.get(1).toString());
        System.out.println(da.playgroundsList.get(0).toString());


        return da;
    }

   /* public void RemovePlaygroundByID(String id){
        da.getPlaygroundsList().remove()
    }*/

   public Playground getPlaygroundByID(String id){
        for(int i=0;i<da.getPlaygroundsList().size();i++)
        {System.out.println("ID: "+da.getPlaygroundsList().get(i).getIdPlayground()+","+id);
            if(da.getPlaygroundsList().get(i).getIdPlayground().equals(id))
            {
                return da.getPlaygroundsList().get(i);
            }
        }
       System.out.println("not found");
       return new Playground(00000,00000);

    }
    public User getUserMe() {
        return userMe;
    }

    public TagList getTags() {
        return tags;
    }

    public ArrayList<Playground> getPlaygroundsList() {
        return playgroundsList;
    }
    //public Hashtable<String,Playground> getPlaygroundsList() {
    //    return playgroundsList;
    //}
    public Playground getPlaygroundsList_prvi() {
        return playgroundsList.get(0);
    }


    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }

    //private

}

