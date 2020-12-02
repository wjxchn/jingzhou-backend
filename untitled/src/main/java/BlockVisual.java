import java.util.Date;

public class BlockVisual {
    long Dynamic64Code;
    long height;
    String preSHA256Code;
    Date time;
    String merkleRoot;
    String body;

    public long getDynamic64Code() {
        return Dynamic64Code;
    }

    public void setDynamic64Code(long dynamic64Code) {
        Dynamic64Code = dynamic64Code;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getPreSHA256Code() {
        return preSHA256Code;
    }

    public void setPreSHA256Code(String preSHA256Code) {
        this.preSHA256Code = preSHA256Code;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
