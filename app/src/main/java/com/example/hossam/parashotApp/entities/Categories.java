package com.example.hossam.parashotApp.entities;

import java.io.Serializable;
import java.util.List;

public class Categories implements Serializable{


    private List<DataBean> Data;
    private List<SliderBean> slider;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public List<SliderBean> getSlider() {
        return slider;
    }

    public void setSlider(List<SliderBean> slider) {
        this.slider = slider;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * name : مطاعم
         * created : 2018-11-01T20:25:08+0000
         * photo : http://parashot.codesroots.com/library/default/15390008811521779367.png
         * modified : 2018-10-08T12:14:41+0000
         * subcats : [{"id":1,"name":"Electronics","category_id":1,"photo":"http://parashot.codesroots.com/library/default/1539777724984562474.png","icon":"v","created":"2018-11-01T20:25:44+0000","modified":"2018-11-01T20:25:48+0000"}]
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

        public static class SubcatsBean {
            /**
             * id : 1
             * name : Electronics
             * category_id : 1
             * photo : http://parashot.codesroots.com/library/default/1539777724984562474.png
             * icon : v
             * created : 2018-11-01T20:25:44+0000
             * modified : 2018-11-01T20:25:48+0000
             */

            private int id;
            private String name;
            private int category_id;
            private String photo;
            private String icon;
            private String created;
            private String modified;

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

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }
        }
    }

    public static class SliderBean implements Serializable{
        /**
         * id : 1
         * photo : http://localhost:8080/newparashote/library/default/15395177111708407597.png
         * design_id : 169
         * created : null
         * modified : null
         */

        private int id;
        private String photo;
        private String design_id;
        private Object created;
        private Object modified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getDesign_id() {
            return design_id;
        }

        public void setDesign_id(String design_id) {
            this.design_id = design_id;
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
