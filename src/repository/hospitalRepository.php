<?php

class hospitalRepository extends dataBaseRepository {
    public function getPasswordByUsername() {
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

    public function getAllExaminationReport() {
        $sql = "
            SELECT * FROM examination a 
            INNER JOIN exam_use_med b 
            ON a.exam_id = b.exam_id
            INNER JOIN medication c
            ON c.m_code = b.m_code
            INNER JOIN medication_effect d 
            ON d.m_code = c.m_code
        ";

        return $this->getDataFromResult($this->queryExecutor($sql));
    }

    public function getAllTreatmentReport() {
        $sql = "
            SELECT * FROM treatment a 
            INNER JOIN treat_use_med b
            ON a.treat_code = b.treat_code
            INNER JOIN medication c 
            ON c.m_code = b.m_code
            INNER JOIN doc_treat_inpa d 
            ON d.treat_code = a.treat_code
            INNER JOIN medication_effect e
            ON e.m_code = c.m_code
        ";

        return($this->getDataFromResult($this->queryExecutor($sql)));
    }

    public function getAllOutPatientInfo() {
        $sql = "
            SELECT * FROM outpatient a
            INNER JOIN examination b 
            ON b.p_char = a.p_char AND b.p_code = a.p_number
        ";

        return $this->getDataFromResult($this->queryExecutor($sql));
    }

    public function getAllInPatienInfo() {
        $sql = "
            SELECT * FROM inpatient a 
            JOIN doc_treat_inpa b 
            ON a.p_char = b.p_char and a.p_number = b.p_number
            JOIN treatment c 
            ON c.treat_code = b.treat_code
            JOIN treat_use_med d 
            ON d.treat_code = c.treat_code
            JOIN medication e 
            ON e.m_code = d.m_code
        ";

        return($this->getDataFromResult($this->queryExecutor($sql)));
    }
}