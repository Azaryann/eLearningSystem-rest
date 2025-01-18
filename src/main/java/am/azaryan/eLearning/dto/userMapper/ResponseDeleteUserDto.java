package am.azaryan.eLearning.dto.userMapper;

public class ResponseDeleteUserDto {

    private boolean deleted;

    public ResponseDeleteUserDto( boolean deleted) {

        this.deleted = deleted;
    }

    public boolean isRemoved() {
        return deleted;
    }

    public void setRemoved(boolean deleted) {
        this.deleted = deleted;
    }

}
