package com.iktpreobuka.ediaryfinal.services;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.ediaryfinal.entities.MarkEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentEntity;
import com.iktpreobuka.ediaryfinal.entities.StudentsCardEntity;
import com.iktpreobuka.ediaryfinal.entities.SubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectClassEntity;
import com.iktpreobuka.ediaryfinal.entities.TeacherSubjectEntity;
import com.iktpreobuka.ediaryfinal.entities.dto.MarkDTO;
import com.iktpreobuka.ediaryfinal.repositories.MarkRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentRepository;
import com.iktpreobuka.ediaryfinal.repositories.StudentsCardRepository;
import com.iktpreobuka.ediaryfinal.repositories.SubjectRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectClassRepository;
import com.iktpreobuka.ediaryfinal.repositories.TeacherSubjectRepository;

@Service
public class MarkServiceImpl implements MarkService{
	
		
		@Autowired
		private MarkRepository markRepo;
		
		@Autowired
		private StudentRepository studentRepo;
		
		@Autowired
		private TeacherRepository teacherRepo;
		
		@Autowired
		private SubjectRepository subjectRepo;
		
		@Autowired
		private TeacherSubjectRepository teacherSubjectRepo;
		
		
		@Autowired
		private TeacherSubjectClassRepository teacherSubjectClassRepo;
		
		
		@Autowired
		private StudentsCardRepository studentsCardRepo;
		
		
		@Autowired
		public JavaMailSender emailSender;
		
		
		public Iterable<MarkEntity> getAllMarks() {
			return markRepo.findAll();
		}

		public Optional<MarkEntity> findById(Integer id) {
			return markRepo.findById(id);
		}

		public MarkEntity addNewMark(MarkEntity newMark) {
			return markRepo.save(newMark);
		}
		
		public MarkEntity updateMark(Integer id, MarkEntity newMark) {
			if (newMark == null || !markRepo.findById(id).isPresent()) {
				return null;
			}
			MarkEntity me = markRepo.findById(id).get();
			me.setMarkType(newMark.getMarkType());
			me.setMarkValue(newMark.getMarkValue());
			me.setStudentsCard(newMark.getStudentsCard());
			return markRepo.save(me);
		}

		public MarkEntity deleteMark(Integer id) {
			if (!markRepo.findById(id).isPresent()) {
				return null;
			}
			MarkEntity me = markRepo.findById(id).get();
			markRepo.deleteById(id);
			return me;
		}
		
		  public List<MarkEntity> findByStudentsCard(Integer studentsCardId){
			  return markRepo.findByStudentsCardId(studentsCardId);
		  }
		  
		 
		public List<MarkEntity> findByTeacherSubjectClass(Integer teacherSubjectClassId){
			return markRepo.findByStudentsCardTeacherSubjectClassId(teacherSubjectClassId);
		}
		
		public void sendEmailToParent(StudentsCardEntity sce, MarkEntity mark) throws MessagingException {
			MimeMessage mail = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(sce.getStudent().getParent().getEmail());
			helper.setSubject("New mark");
			String studentName = sce.getStudent().getFirstName() + sce.getStudent().getLastName();
			String table = "<html>\r\n" + 
					"<body>\r\n" + 
					"	<br><table border=\"4px\">\r\n" + 
					"		<tr>\r\n" + 
					"			<th>Subject</th>\r\n" + 
					"			<th>Mark</th>\r\n" + 
					"		</tr>\r\n" + 
					"		<tr>\r\n" + 
					"			<td>" + sce.getTeacherSubjectClass().getTeacherSubject().getSubject().getName() + "</td>\r\n" + 
					"			<td>" + mark.getMarkValue() + "</td>\r\n" + 
					"		</tr>\r\n" + 
					"	</table>\r\n" + 
					"</body>\r\n" + 
					"<br></html>";
			String text = "Greetings, your child " + studentName + " has received new grade:" + table + "Regards, school administration.";
			helper.setText(text, true);
			
			try {
				emailSender.send(mail);
			} catch (Exception e) {
				e.getMessage();
			}
		}
}