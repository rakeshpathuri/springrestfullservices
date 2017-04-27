package com.example;

/**
 * Created by rakes on 4/24/2017.
 */
public class Greeting {


        private  long id;
        private  String content;

        public Greeting(long id, String content) {
            this.id = id;
            this.content = content;
        }
    public Greeting() {

    }

        public long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    public void setContent(String name) {
            content = name;
    }
}
