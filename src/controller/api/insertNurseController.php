<?php

class insertNurseController extends apiController
{
    public function POST() {
        $this->hospitalRepository->insertNurse($this->requestBody);

        $this->responseJsonData("Thêm y tá thành công");
    }
}