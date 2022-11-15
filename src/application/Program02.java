package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJdbc;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program02 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("===== Implementação findDepartmentById =====\n");
        System.out.print("Escolha o numero de um departamento: ");
        Integer depId = sc.nextInt();
        sc.nextLine();
        Department department = departmentDao.findById(depId);
        System.out.println(department);
        System.out.println("\n===== CONSULTA FINALIZADA =====\n");

        System.out.println("===== Implementação findAll =====\n");
        List<Department> list = departmentDao.findAll();
        for(Department deps : list){
            System.out.println(deps);
        }
        System.out.println("\n===== CONSULTA FINALIZADA =====\n");

        System.out.println("===== Implementação Inserção =====\n");
        System.out.print("Qual o nome do novo departamento? ");
        String name = sc.nextLine();
        Department newDepartment = new Department(null,name);
        departmentDao.insert(newDepartment);
        System.out.println("\n===== INSERÇÃO FINALIZADA =====\n");

        System.out.println("===== Implementação Update =====\n");
        System.out.print("Qual o nome novo do departamento? ");
        name = sc.nextLine();
        System.out.print("Qual o ID do departamento? ");
        depId = sc.nextInt();
        department = departmentDao.findById(depId);
        department.setName(name);
        departmentDao.update(department);
        System.out.println("\n===== UPDATE FINALIZADO =====\n");

        System.out.println("===== Implementação Delete =====\n");
        System.out.print("Qual o ID do departamento a ser excluido? ");
        depId = sc.nextInt();
        departmentDao.deleteById(depId);
        System.out.println("\n===== DELETE FINALIZADO =====\n");

        System.out.println("Lista Atualizada: ");
        list = departmentDao.findAll();
        for(Department deps : list){
            System.out.println(deps);
        }

        sc.close();
    }
}
