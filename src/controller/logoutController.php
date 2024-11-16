<?php

class logoutController extends Controller {
    public function GET () {
        setcookie('token', '', time() - 3600, '/');

        $this->responseJsonData("Đăng xuất thành công");
    }
}