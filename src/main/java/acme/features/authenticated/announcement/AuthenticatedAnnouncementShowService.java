package acme.features.authenticated.announcement;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAnnouncementShowService  implements AbstractShowService<Authenticated, Announcement>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedAnnouncementRepository repository;

		// AbstractShowService<Authenticated, Announcement> interface --------------


		@Override
		public boolean authorise(final Request<Announcement> request) {
			assert request != null;
			
			boolean result;
			Calendar calendar;
			Date deadline;
			Announcement announcement;
			int id;
			
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			deadline = calendar.getTime();
			
			id = request.getModel().getInteger("id");
			announcement = this.repository.findAnnouncementById(id);
			result = announcement.getCreationMoment().after(deadline);

			return result;
		}

		@Override
		public Announcement findOne(final Request<Announcement> request) {
			assert request != null;

			Announcement result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findAnnouncementById(id);

			return result;
		}

		@Override
		public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "creationMoment", "body","criticalFlag", "link");
		}
	
	

}