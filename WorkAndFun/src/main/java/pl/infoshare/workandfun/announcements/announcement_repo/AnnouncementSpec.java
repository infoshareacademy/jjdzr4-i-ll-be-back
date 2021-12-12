package pl.infoshare.workandfun.announcements.announcement_repo;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

@And({
        @Spec(path = "type", spec = Equal.class),
        @Spec(path = "serviceType", spec = Equal.class),
        @Spec(path = "header", spec = LikeIgnoreCase.class),
        @Spec(path = "city", spec = Equal.class),
        @Spec(path = "cityDistrict", spec = Equal.class),
        @Spec(path = "unit", spec = Equal.class),
//        @Spec(path = "price", spec = Like.class), TODO
        @Spec(path = "voivodeship", spec = Equal.class),
        @Spec(path = "description", spec = LikeIgnoreCase.class)

})
public interface AnnouncementSpec extends Specification<Announcement> {
}
