package in.semibit.webviewautomation;

public class Profile {


    private String url;
    private String name;
    private String role;
    private String location;
    private String img;
    private String passoutYear;
    private String passoutFrom;
    private String passoutLocation;

    public static class Builder {

        private String url;
        private String name;
        private String role;
        private String location;
        private String img;
        private String passoutYear;
        private String passoutFrom;
        private String passoutLocation;

        public Builder() {
        }

        Builder(String url, String name, String role, String location, String img, String passoutYear, String passoutFrom, String passoutLocation) {
            this.url = url;
            this.name = name;
            this.role = role;
            this.location = location;
            this.img = img;
            this.passoutYear = passoutYear;
            this.passoutFrom = passoutFrom;
            this.passoutLocation = passoutLocation;
        }

        public Builder url(String url){
            this.url = url;
            return Builder.this;
        }

        public Builder name(String name){
            this.name = name;
            return Builder.this;
        }

        public Builder role(String role){
            this.role = role;
            return Builder.this;
        }

        public Builder location(String location){
            this.location = location;
            return Builder.this;
        }

        public Builder img(String img){
            this.img = img;
            return Builder.this;
        }

        public Builder passoutYear(String passoutYear){
            this.passoutYear = passoutYear;
            return Builder.this;
        }

        public Builder passoutFrom(String passoutFrom){
            this.passoutFrom = passoutFrom;
            return Builder.this;
        }

        public Builder passoutLocation(String passoutLocation){
            this.passoutLocation = passoutLocation;
            return Builder.this;
        }

        public Profile build() {
            if(this.url == null){
                throw new NullPointerException("The property \"url\" is null. "
                        + "Please set the value by \"url()\". "
                        + "The property \"url\" is required.");
            }

            return new Profile(this);
        }
    }

    private Profile(Builder builder) {
        this.url = builder.url;
        this.name = builder.name;
        this.role = builder.role;
        this.location = builder.location;
        this.img = builder.img;
        this.passoutYear = builder.passoutYear;
        this.passoutFrom = builder.passoutFrom;
        this.passoutLocation = builder.passoutLocation;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPassoutYear() {
        return passoutYear;
    }

    public void setPassoutYear(String passoutYear) {
        this.passoutYear = passoutYear;
    }

    public String getPassoutFrom() {
        return passoutFrom;
    }

    public void setPassoutFrom(String passoutFrom) {
        this.passoutFrom = passoutFrom;
    }

    public String getPassoutLocation() {
        return passoutLocation;
    }

    public void setPassoutLocation(String passoutLocation) {
        this.passoutLocation = passoutLocation;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", location='" + location + '\'' +
                ", img='" + img + '\'' +
                ", passoutYear='" + passoutYear + '\'' +
                ", passoutFrom='" + passoutFrom + '\'' +
                ", passoutLocation='" + passoutLocation + '\'' +
                '}';
    }
}
