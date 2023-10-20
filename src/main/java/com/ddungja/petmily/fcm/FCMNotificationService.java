package com.ddungja.petmily.fcm;

import com.ddungja.petmily.fcm.request.FCMNotificationRequest;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.service.port.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository usersRepository;

    public String sendNotificationByToken(FCMNotificationRequest request) {

        Optional<User> user = usersRepository.findById(request.getTargetUserId());

        if (user.isPresent()) {
            if (user.get().getFirebaseToken() != null) {
                Notification notification = Notification.builder()
                        .setTitle(request.getTitle())
                        .setBody(request.getBody())
                        // .setImage(request.getImage())
                        .build();

                Message message = Message.builder()
                        .setToken(user.get().getFirebaseToken())
                        .setNotification(notification)
                        // .putAllData(request.getData())
                        .build();

                try {
                    firebaseMessaging.send(message);
                    return "알림을 성공적으로 전송했습니다. targetUserId=" + request.getTargetUserId();
                } catch (FirebaseMessagingException e) {
                    e.printStackTrace();
                    return "알림 보내기를 실패하였습니다. targetUserId=" + request.getTargetUserId();
                }
            } else {
                return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + request.getTargetUserId();
            }

        } else {
            return "해당 유저가 존재하지 않습니다. targetUserId=" + request.getTargetUserId();
        }


    }
}