<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class User extends CI_Controller {

  public function __construct() {

    parent::__construct();
    parent::__construct();
    $this->load->helper('url');
    $this->load->model('users_model');
  }

  public function index(){

 $this->home();
 
  }

   public function home(){
    $data['content'] = "Home Page";
    $this->load->view('includes/header.php');
    $this->load->view('index',$data);
    $this->load->view('includes/footer.php');
  }

     public function about(){
          $this->load->view('includes/header.php');
  $this->load->view('about');
      $this->load->view('includes/footer.php');
  }
      public function gogapi(){
      $this->load->database();
      $this->load->view('includes/header.php');
      $this->load->view('gogapi');
      $this->load->view('includes/footer.php');
  }


  public function map(){
      $this->load->database();
      $this->load->view('includes/header.php');
      $this->load->view('map');
      $this->load->view('includes/footer.php');
  }

  public function partner(){
      $this->load->database();$this->load->library('session');

    //restrict users to go back to login if session has been set
    if($this->session->userdata('user')){
      redirect('user/partnerhome');
    }
    else{
      $this->load->view('login_page');
    }
  }


  public function login(){
    //load session library
    $this->load->library('session');

    $email = $_POST['email'];
    $password = $_POST['password'];

    $data = $this->users_model->login($email, $password);

    if($data){
      $this->session->set_userdata('user', $data);
      redirect('user/partnerhome');
    }
    else{
      header('location:'.base_url().$this->index());
      $this->session->set_flashdata('error','Invalid login. User not found');
    } 
  }

  public function partnerhome(){
    //load session library
    $this->load->library('session');

    //restrict users to go to home if not logged in
    if($this->session->userdata('user')){
      $this->load->view('includes/header3.php');
      $this->load->view('home');
      $this->load->view('includes/header3.php');
    }
    else{
      redirect('/');
    }
    
  }

  public function logout(){
    //load session library
    $this->load->library('session');
    $this->session->unset_userdata('user');
    redirect('/');
  }

}

?>
