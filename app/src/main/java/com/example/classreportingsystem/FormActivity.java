package com.example.classreportingsystem;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.util.Log;

public class FormActivity extends AppCompatActivity {

    private EditText dateField, timeField, facultyNameField, branchField, semesterField, subjectField;
    private EditText classEngagedOfField, topicsField, attendanceField, remarksField;
    private Button submitButton;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Initialize views
        dateField = findViewById(R.id.editTextDate);
        timeField = findViewById(R.id.editTextTime);
        facultyNameField = findViewById(R.id.editTextFacultyName);
        branchField = findViewById(R.id.editTextBranch);
        semesterField = findViewById(R.id.editTextSemester);
        subjectField = findViewById(R.id.editTextSubject);
        classEngagedOfField = findViewById(R.id.editTextClassEngagedOf);
        topicsField = findViewById(R.id.editTextTopics);
        attendanceField = findViewById(R.id.editTextAttendance);
        remarksField = findViewById(R.id.editTextRemarks);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    generatePDF();
                } else {
                    requestPermission();
                }
            }
        });
    }

    private void generatePDF() {
        // Get the text from EditText fields
        String date = dateField.getText().toString();
        String time = timeField.getText().toString();
        String facultyName = facultyNameField.getText().toString();
        String branch = branchField.getText().toString();
        String semester = semesterField.getText().toString();
        String subject = subjectField.getText().toString();
        String classEngagedOf = classEngagedOfField.getText().toString();
        String topics = topicsField.getText().toString();
        String attendance = attendanceField.getText().toString();
        String remarks = remarksField.getText().toString();

        // Create a new Document
        Document document = new Document();

        try {
            // Get the directory for the app's private documents
            String path = getExternalFilesDir(null).getAbsolutePath() + "/ClassReportingSystem";
            java.io.File dir = new java.io.File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create a unique filename using current timestamp
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String filename = "Report_" + timeStamp + ".pdf";

            // Create the PDF file
            PdfWriter.getInstance(document, new FileOutputStream(path + "/" + filename));

            // Open the document
            document.open();

            // Add content to the document
            document.add(new Paragraph("Date: " + date));
            document.add(new Paragraph("Time: " + time));
            document.add(new Paragraph("Faculty Name: " + facultyName));
            document.add(new Paragraph("Branch: " + branch));
            document.add(new Paragraph("Semester: " + semester));
            document.add(new Paragraph("Subject: " + subject));
            document.add(new Paragraph("Class Engaged Of: " + classEngagedOf));
            document.add(new Paragraph("Topics Covered: " + topics));
            document.add(new Paragraph("Attendance: " + attendance));
            document.add(new Paragraph("Remarks: " + remarks));

            // Close the document
            document.close();

            Toast.makeText(this, "PDF generated successfully: " + filename, Toast.LENGTH_LONG).show();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error generating PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
}
