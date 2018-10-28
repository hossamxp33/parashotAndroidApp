package com.example.hossam.parashotApp.entities;

import java.io.Serializable;
import java.util.List;

public class Categories implements Serializable{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * name : مطاعم
         * created : 2018-10-11T02:00:00+0000
         * photo : burger.png
         * modified : 2018-10-08T12:14:41+0000
         * subcats : [{"id":1,"name":"Electronics","category_id":1,"photo":"","icon":"","created":null,"modified":null}]
         */

        private int id;
        private String name;
        private String created;
        private String photo;
        private String modified;
        private List<SubcatsBean> subcats;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public List<SubcatsBean> getSubcats() {
            return subcats;
        }

        public void setSubcats(List<SubcatsBean> subcats) {
            this.subcats = subcats;
        }

        public static class SubcatsBean implements Serializable {
            /**
             * id : 1
             * name : Electronics
             * category_id : 1
             * photo :
             * icon :
             * created : null
             * modified : null
             */

            private int id;
            private String name;
            private int category_id;
            private String photo;
            private String icon;
            private Object created;
            private Object modified;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getCreated() {
                return created;
            }

            public void setCreated(Object created) {
                this.created = created;
            }

            public Object getModified() {
                return modified;
            }

            public void setModified(Object modified) {
                this.modified = modified;
            }
        }
    }
}
