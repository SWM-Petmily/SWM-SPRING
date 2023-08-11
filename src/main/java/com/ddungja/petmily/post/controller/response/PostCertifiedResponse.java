package com.ddungja.petmily.post.controller.response;

import com.ddungja.petmily.post.domain.Post;
import com.ddungja.petmily.post.domain.type.CertifiedType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCertifiedResponse {

    private final Long postId;
    private final CertifiedType isRegistered;
    private final CertifiedType isVaccinated;
    private final CertifiedType isMedicalChecked;

    @Builder
    public PostCertifiedResponse(Long postId, CertifiedType isRegistered, CertifiedType isVaccinated, CertifiedType isMedicalChecked) {
        this.postId = postId;
        this.isRegistered = isRegistered;
        this.isVaccinated = isVaccinated;
        this.isMedicalChecked = isMedicalChecked;
    }

    public static PostCertifiedResponse from(Post post) {
        return PostCertifiedResponse.builder()
                .postId(post.getId())
                .isRegistered(post.getIsRegistered())
                .isVaccinated(post.getIsVaccinated())
                .isMedicalChecked(post.getIsMedicalChecked())
                .build();
    }
}
