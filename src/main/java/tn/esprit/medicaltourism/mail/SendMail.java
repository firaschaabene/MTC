package tn.esprit.medicaltourism.mail;

public class SendMail {

	public static void main(String[] args) {
		String message = Message.sms;

		String[] to = { "Mohamed.chaouch@esprit.tn","houssaien.bensalem@esprit.tn" };

		if (EmailSender.sendEmail("pigaleriedesarts@gmail.com", "ScrumMaster",
				message, to))
			System.out.println("email sent successfully");
		else
			System.out.println("some error occured");

	}
}
