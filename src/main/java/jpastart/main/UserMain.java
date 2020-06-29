package jpastart.main;

import jpastart.jpa.EMF;
import jpastart.reserve.application.*;
import jpastart.reserve.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserMain {
    private static JoinService joinService = new JoinService();
    private static GetUserService getUserService = new GetUserService();
    private static ChangeNameService changeNameService = new ChangeNameService();
    private static GetUserListService listService = new GetUserListService();
    private static WithdrawService withdrawService = new WithdrawService();

    public static void main(String[] args) throws IOException {
        EMF.init();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("명령어를 입력하세요:");
            String line = bufferedReader.readLine();
            String[] commands = line.split(" ");
            if (commands[0].equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            } else if (commands[0].equalsIgnoreCase("join")) {
                handleJoinCommand(commands);
            } else if (commands[0].equalsIgnoreCase("view")) {
                handleViewCommand(commands);
            } else if (commands[0].equalsIgnoreCase("list")) {
                handleListCommand();
            } else if (commands[0].equalsIgnoreCase("changename")) {
                handleChangeName(commands);
            } else if (commands[0].equalsIgnoreCase("withdraw")) {
                handleWithdrawCommand(commands);
            } else {
                System.out.println("올바른 명령어를 입력하세요.");
            }
            System.out.println("----");
        }
        EMF.close();
    }

    private static void handleWithdrawCommand(String[] command) {
        if (command.length != 2) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법 : withdraw 이메일");
            return;
        }
        try {
            withdrawService.withdraw(command[1]);
            System.out.println("탈퇴처리 했습니다.");
        } catch (UserNotFoundException e) {
            System.out.println("존재하지 않습니다.");
        }
    }

    private static void handleListCommand() {
        List<User> allUsers = listService.getAllUsers();
        if (allUsers.isEmpty()) {
            System.out.println("사용자가 없습니다.");
        } else {
            allUsers.forEach(user -> System.out.printf("| %s | %s | %tY-%<td |\n", user.getEmail(), user.getName(), user.getCreatedDate()));
        }
    }

    private static void handleChangeName(final String[] commands) {
        if (commands.length != 3) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법:change 이메일 새이름");
            return;
        }
        try {
            changeNameService.changeName(commands[1], commands[2]);
            System.out.println("이름을 변경했습니다.");
        } catch (UserNotFoundException e) {
            System.out.println("존재하지 않습니다.");
        }
    }

    private static void handleViewCommand(final String[] commands) {
        if (commands.length != 2) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법 : view 이메일");
            return;
        }
        Optional<User> userOpt = getUserService.getUser(commands[1]);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("이름 : " + user.getName());
            System.out.printf("생성 : %tY-%<tm-%<td\n", user.getCreatedDate());
        } else {
            System.out.println("존재하지 않습니다.");
        }
    }

    private static void handleJoinCommand(final String[] commands) {
        if (commands.length != 3) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법:join 이메일 이름");
            return;
        }
        try {
            joinService.join(new User(commands[1], commands[2], new Date()));
            System.out.println("가입 요청을 처리했습니다.");
        } catch (DuplicateEmailException e) {
            System.out.println("이미 같은 이메일을 가진 사용자가 존재합니다.");
        }
    }
}
