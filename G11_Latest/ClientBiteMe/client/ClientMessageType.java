package client;
/**
 * ClientMessageType for the type of the client message
 */
public enum ClientMessageType {
	Disconnected,
	Logout,
	CONNECTION,
	Login,
	LOGIN_PERSON,
	LOGIN_TEACHER,
	LOGIN_STUDENT,
	TEACHER_SUBJECTS_INFORMATION,
	TEACHER_COURSES_INFORMATION,
	PRINCIPAL_SUBJECTS_INFORMATION,
	PRINCIPAL_COURSES_INFORMATION,
	PRINCIPAL_STUDENTS_INFORMATION,
	PRINCIPAL_TEACHERS_INFORMATION,
	PRINCIPAL_REPORT_COURSES_INFORMATION,
	PRINCIPAL_REPORT_STUDENT_INFORMATION,  //still need to add controllers (kfir note)
	PRINCIPAL_REPORT_TEACHER_INFORMATION,  //this too.
	PRINCIPAL_REQUESTS_INFORMATION,
	PRINCIPAL_APPROVED_REQUESTS_UPDATE,
	PRINCIPAL_DECLINED_REQUESTS_UPDATE,
	PRINCIPAL_CHECK_REQUESTS_COUNTING,
	PRINCIPAL_UPDATE_REQUESTS_STATUS,
	GET_SOLVED_EXAM_BY_COURSE,
	VALIDATE_EXAM_CODE,
	GET_EXAM_INFORMATION,
	GET_QUESTION_BY_COURSE,
	GET_QUESTION_BY_EXAM,
	GET_EXAM_BY_COURSE,
	TEACHER_ADD_QUESTION,
	TEACHER_ADD_EXAM,
	TEACHER_CHECK_VALID_CODE,
	TEACHER_DELETE_QUESTION,
	UPLOAD_TEACHER_MANUAL_EXAM,
	//VALIDATE_STUDENT_ID,
	UPLOAD_MANUAL_EXAM,
	DOWNLOAD_MANUAL_EXAM,
	GET_EXAM_QUESTIONS,
	INSERT_EXAM_QUESTIONS,
	INSERT_EXAM_TO_DB, 
	STUDENT_COURSES_INFORMATION,
	STUDENT_SUBJECTS_INFORMATION,
	TEAHCER_GET_SOLVED_EXAMS_BY_COURSE,//input: course, output: arraylist of exams types
	TEAHCER_GET_SOLVED_EXAMS_BY_EXAM_TYPE,//input: exam type, output: arraylist of sovledExams
	TEACHER_GET_SOLVED_EXAM_QUESTIONS_BY_STUDENT,//input solvedExam output: arraylist of solvedQuestions.
	TEACHER_GET_CURRENT_EXAM,
	TEACHER_GET_REPORT_EXAM_LIST,
	TEACHER_REPORT_DATA,
	TEACHER_SEND_REQUEST,
	GET_QUESTIONS_FOR_SOLVED_EXAM,
	GET_SOLVED_EXAMS,
	TEACHER_EDIT_QUESTION,
	TEACHER_ADD_GRADE,
	TEACHER_PUBLISH_EXAM,
	TEACHER_LOCK_EXAM,
	TEACHER_GET_EXAMS,
	TEACHER_ADD_GRADE_WITH_COMMENTS,
	LOGIN,
	UPDATE_ORDER;
}

