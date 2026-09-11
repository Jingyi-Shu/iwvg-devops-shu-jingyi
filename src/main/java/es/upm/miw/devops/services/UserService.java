package es.upm.miw.devops.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    // 如果已有 UserRepository，在此处注入；没有则先返回模拟数据或对接 Repository
    public Object read(String id) {
        // TODO: 调用 userRepository.findById(id)
        return null;
    }
}