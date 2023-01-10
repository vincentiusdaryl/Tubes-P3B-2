package com.pppb.if_apps.Model;

import java.util.ArrayList;

public class GetPengumuman {
    public ArrayList<Content> pengumuman = new ArrayList<>();

    public class Content {
        public String id;
        public String title;
        public String updated_at;
        public String created_at;
        public String author;
        public String[] tags;
        public String[] tag_id;
    }

}
