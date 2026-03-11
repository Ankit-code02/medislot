package com.medislot.service;

import com.medislot.entity.Appointment;
import com.medislot.enums.AppointmentStatus;
import com.medislot.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailReminderService {

    private final AppointmentRepository appointmentRepository;
    private final JavaMailSender mailSender;

    @Scheduled(cron = "0 0 9 * * *")
    public void sendAppointmentReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        List<Appointment> appointments = appointmentRepository
                .findByDoctorIdAndAppointmentDate(0L, tomorrow)
                .stream()
                .filter(a -> a.getStatus() == AppointmentStatus.CONFIRMED)
                .toList();

        List<Appointment> allTomorrow = appointmentRepository
                .findAll()
                .stream()
                .filter(a -> a.getAppointmentDate().equals(tomorrow))
                .filter(a -> a.getStatus() == AppointmentStatus.CONFIRMED)
                .toList();

        for (Appointment appointment : allTomorrow) {
            sendReminderToPatient(appointment);
            sendReminderToDoctor(appointment);
        }

        log.info("Sent {} appointment reminders for {}",
                allTomorrow.size(), tomorrow);
    }

    private void sendReminderToPatient(Appointment appointment) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(appointment.getPatient().getUser().getEmail());
            message.setSubject("🏥 MediSlot - Appointment Reminder");
            message.setText(
                    "Dear " + appointment.getPatient().getUser().getFullName() + ",\n\n" +
                            "This is a reminder for your appointment tomorrow!\n\n" +
                            "Doctor: " + appointment.getDoctor().getUser().getFullName() + "\n" +
                            "Specialization: " + appointment.getDoctor().getSpecialization() + "\n" +
                            "Date: " + appointment.getAppointmentDate() + "\n" +
                            "Time: " + appointment.getAppointmentTime() + "\n" +
                            "Reason: " + appointment.getReason() + "\n\n" +
                            "Please arrive 10 minutes early.\n\n" +
                            "Best regards,\nMediSlot Team"
            );
            mailSender.send(message);
            log.info("Reminder sent to patient: {}",
                    appointment.getPatient().getUser().getEmail());
        } catch (Exception e) {
            log.error("Failed to send reminder to patient: {}", e.getMessage());
        }
    }

    private void sendReminderToDoctor(Appointment appointment) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(appointment.getDoctor().getUser().getEmail());
            message.setSubject("🏥 MediSlot - Patient Appointment Tomorrow");
            message.setText(
                    "Dear " + appointment.getDoctor().getUser().getFullName() + ",\n\n" +
                            "You have a patient appointment tomorrow!\n\n" +
                            "Patient: " + appointment.getPatient().getUser().getFullName() + "\n" +
                            "Date: " + appointment.getAppointmentDate() + "\n" +
                            "Time: " + appointment.getAppointmentTime() + "\n" +
                            "Reason: " + appointment.getReason() + "\n\n" +
                            "Best regards,\nMediSlot Team"
            );
            mailSender.send(message);
            log.info("Reminder sent to doctor: {}",
                    appointment.getDoctor().getUser().getEmail());
        } catch (Exception e) {
            log.error("Failed to send reminder to doctor: {}", e.getMessage());
        }
    }
}