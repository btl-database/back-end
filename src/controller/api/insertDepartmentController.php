<?php

class insertDepartmentController extends apiController {
    public function POST () {
        $this->hospitalRepository->insertDepartment($this->requestBody);

        $this->responseJsonData("Thêm department thành công");
    }
}