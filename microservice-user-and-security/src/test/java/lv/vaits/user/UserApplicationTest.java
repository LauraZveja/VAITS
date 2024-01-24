package lv.vaits.user;

import lv.vaits.user.repos.users.IAuthorityRepository;
import lv.vaits.user.repos.users.IUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserApplicationTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IAuthorityRepository authorityRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserApplication userApplication;

    @Test
    void main() {
        assertDoesNotThrow(() -> UserApplication.main(new String[]{}));
    }

    @Test
    void passwordEncoderSimple() {
        assertNotNull(userApplication.passwordEncoderSimple());
    }

    @Test
    void testDB() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        assertDoesNotThrow(() -> userApplication.testDB(userRepository, authorityRepository).run());
        verify(userRepository, times(10)).save(any());
        verify(authorityRepository, times(2)).save(any());
    }
}
