<?php

class loginController extends Controller {
    public function PUT () {
        $data = $this->hospitalRepository->getPasswordByUsername($this->requestBody['username'])[0];

        if($data['password'] == $this->requestBody['password']) {
            $token = jwtService::createToken($this->requestBody['username']);

            setcookie('token', $token, time() + 36000, "/");

            $this->responseJsonData(['token' => $token]);
        }

        $this->responseJsonData('Sai mật khẩu', 401);
    }
}