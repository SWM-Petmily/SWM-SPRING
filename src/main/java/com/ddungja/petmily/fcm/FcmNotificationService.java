package com.ddungja.petmily.fcm;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.fcm.request.FCMNotificationRequest;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.repository.FcmRepository;
import com.ddungja.petmily.user.service.port.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ddungja.petmily.common.exception.ExceptionCode.FCM_SEND_FAIL;
import static com.ddungja.petmily.common.exception.ExceptionCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FcmNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository usersRepository;
    private final FcmRepository fcmRepository;

    public void sendNotificationByToken(FCMNotificationRequest request) {
        try {
            User user = usersRepository.findById(request.getTargetUserId()).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
            Notification notification = Notification.builder()
                    .setTitle(request.getTitle())
                    .setBody(request.getBody())
                    // .setImage(request.getImage())
                    .build();

//            for (Fcm fcm : user.getFcms()) {
                Message message = Message.builder()
//                        .setToken(fcm.getToken())
                        .setNotification(notification)
                        // .putAllData(request.getData())
                        .build();
                firebaseMessaging.send(message);
//            }
        } catch (FirebaseMessagingException e) {
            throw new CustomException(FCM_SEND_FAIL);
        }
    }
}