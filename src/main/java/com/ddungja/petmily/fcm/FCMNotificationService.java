package com.ddungja.petmily.fcm;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.common.exception.ExceptionCode;
import com.ddungja.petmily.fcm.request.FCMNotificationRequest;
import com.ddungja.petmily.user.domain.user.Fcm;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository usersRepository;

    public void sendNotificationByToken(FCMNotificationRequest request) throws FirebaseMessagingException {

        User user = usersRepository.findById(request.getTargetUserId()).orElseThrow(() -> new CustomException(ExceptionCode.USER_NOT_FOUND));
        for (Fcm fcm : user.getFcms()) {

            Notification notification = Notification.builder()
                    .setTitle(request.getTitle())
                    .setBody(request.getBody())
                    // .setImage(request.getImage())
                    .build();

            Message message = Message.builder()
                    .setToken(fcm.getToken())
                    .setNotification(notification)
                    // .putAllData(request.getData())
                    .build();

            firebaseMessaging.send(message);

        }
    }
}

