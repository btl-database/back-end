<?php

class hospitalRepository extends dataBaseRepository {
    public function getPasswordByUsername($username) {
        $sql = "
            SELECT password FROM user_data
            WHERE username = 'admin'
        ";

        return $this->getDataFromResult($this->queryExecutor($sql));
    }

    public function insertDoter($form) {
        $sql = "
            INSERT INTO doctor (e_first_name, e_last_name, e_dob, e_gender, e_address, e_start_day, s_name, s_year)
            VALUES ('" . $form['e_first_name'] . "', '" . $form['e_last_name'] . "', '" . $form['e_dob'] . "', '" . $form['e_gender'] . "', '" . $form['e_address'] . "', '" . $form['e_start_day'] . "', '" . $form['s_name'] . "', '" . $form['s_year'] . "')
        ";

        $this->queryExecutor($sql);
    }

    public function insertNurse($form) {
        $sql = "
            INSERT INTO nurse (e_first_name, e_last_name, e_dob, e_gender, e_address, e_start_day, s_name, s_year)
            VALUES ('" . $form['e_first_name'] . "', '" . $form['e_last_name'] . "', '" . $form['e_dob'] . "', '" . $form['e_gender'] . "', '" . $form['e_address'] . "', '" . $form['e_start_day'] . "', '" . $form['s_name'] . "', '" . $form['s_year'] . "')
        ";

        $this->queryExecutor($sql);
    }

    public function insertDepartment($form) {
        $sql = "INSERT INTO department (d_name, dean_doctor_code) 
        VALUES ('" . $form['d_name'] . "', '" . $form['dean_doctor_code'] . "')
        ";

        $this->queryExecutor($sql);
    }

    public function updateEmployeeDepartment($form) {
        $department_code = $form["department_code"];
        $ecode  = $form["ecode"];
        $role = $form["role"];

        $sql = "
            UPDATE $role
            SET department_code = '$department_code'
            WHERE ecode = '$ecode';
        ";

        $this->queryExecutor($sql);
    }

    public function insertInpatient($form) {
        $sql = "
            INSERT INTO inpatient (p_first_name, p_last_name, p_gender, p_dob, p_address, p_phone_number, nurse_code)
            VALUES (
                '{$form['p_first_name']}',
                '{$form['p_last_name']}',
                '{$form['p_gender']}',
                '{$form['p_dob']}',
                '{$form['p_address']}',
                '{$form['p_phone_number']}',
                '{$form['nurse_code']}'
            )
        ";

        $this->queryExecutor($sql);
    }

    public function insertOutpatient($form) {
        $sql = "
            INSERT INTO outpatient (p_first_name, p_last_name, p_gender, p_dob, p_address, p_phone_number, doctor_code)
            VALUES (
                '{$form['p_first_name']}',
                '{$form['p_last_name']}',
                '{$form['p_gender']}',
                '{$form['p_dob']}',
                '{$form['p_address']}',
                '{$form['p_phone_number']}',
                '{$form['doctor_code']}'
            )
        ";

        $this->queryExecutor($sql);
    }

    public function findAllPatientTreatedByADoctor($doctor_code) {
        $sql = "
            SELECT p_char, p_number, p_first_name, p_last_name, p_gender, 	p_dob, p_address, p_phone_number 
            FROM doctor
            INNER JOIN outpatient
            ON doctor.ecode = outpatient.doctor_code
            WHERE doctor.ecode = '$doctor_code'
        ";

        return $this->getDataFromResult($this->queryExecutor($sql));
    }
}