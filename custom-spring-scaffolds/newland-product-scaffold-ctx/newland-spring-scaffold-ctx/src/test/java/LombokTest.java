import com.fasterxml.jackson.databind.ObjectMapper;
import com.nlecloud.spring.annotation.TenantInfo;
import net.github.fastdev.boot.utils.JacksonUtils;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年09月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class LombokTest {


    public static void main(String[] args) {

        TenantInfo tenant = TenantInfo.builder().id(11L).build();
        String json = JacksonUtils.toJson(tenant);
        TenantInfo bean = JacksonUtils.toBean(json, TenantInfo.class);
        System.out.println();
    }
}
