package vn.bkdn.cntt.controller.APIEntity;

/**
 * Created by Tri on 3/31/2017.
 */
public class EditStudentNote {
    private int lessonId;
    private String editedNote;

    public EditStudentNote() {
    }

    public EditStudentNote(int lessonId, String editedNote) {
        this.lessonId = lessonId;
        this.editedNote = editedNote;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getEditedNote() {
        return editedNote;
    }

    public void setEditedNote(String editedNote) {
        this.editedNote = editedNote;
    }
}
