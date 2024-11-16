<?php

class updateDepartmentController extends apiController {
    public function PATCH() {
        $this->hospitalRepository->updateEmployeeDepartment($this->requestBody);

        $this->responseJsonData("Cập nhật department thành công");
    }
}