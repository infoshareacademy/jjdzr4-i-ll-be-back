package pl.infoshare.workandfun.announcements.announcement_repo;

import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

@And({
        @Spec(path = "type", spec = EqualIgnoreCase.class),
        @Spec(path = "serviceType", spec = EqualIgnoreCase.class),
        @Spec(path = "header", spec = LikeIgnoreCase.class),
        @Spec(path = "city", spec = EqualIgnoreCase.class),
        @Spec(path = "cityDistrict", spec = EqualIgnoreCase.class),
        @Spec(path = "unit", spec = EqualIgnoreCase.class),
//        @Spec(path = "price", spec = Like.class), TODO
        @Spec(path = "voivodeship", spec = EqualIgnoreCase.class),
        @Spec(path = "description", spec = LikeIgnoreCase.class)
})

public interface AnnouncementSpec extends Specification<Announcement> {
}
