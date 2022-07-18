package peaksoft;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userDaoJdbc = new UserServiceImpl();
//        Util.connection();
        userDaoJdbc.createUsersTable();
//        userDaoJdbc.dropUsersTable();

//        userDaoJdbc.cleanUsersTable();
//        userDaoJdbc.removeUserById(10);
//        userDaoJdbc.removeUserById(2);
        userDaoJdbc.saveUser("Nurmuhammad","Nurbekov",(byte)21);

//        userDaoJdbc.getAllUsers().forEach(System.out::println);
//
    }
}

//  Alishev