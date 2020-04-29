<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<footer id="footer">
   <div class="container">
       <div class="row">
           <div class="col-sm-12 text-center bottom-separator">
               <img src="${ pageContext.request.contextPath }/resources/images/home/under.png" class="${ pageContext.request.contextPath }/resources/img-responsive inline" alt="">
           </div>
           <div class="col-md-4 col-sm-6">
            <div class="testimonial bottom">
                <!-- 이거 살리는게 나을거같아요 -->
                    <h2 style="font-family:cookierun;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MOHAGEE?</h2>
                    <div class="media">
                        <div class="pull-left">
                            <a href="#"><img src="${ pageContext.request.contextPath }/resources/images/footer.png" alt=""></a>
                        </div>
                        <div class="media-body">
                            <blockquote>어제와 같은 오늘을 보내고 오늘과 다른 내일을 기대하는것은 정신병 초기증세다</blockquote>
                            <h3><a href="#">- EDITOR HOWL</a></h3>
                        </div>
                     </div>
                    <div class="media">
                        <div class="pull-left">
                            <a href="#"><img src="${ pageContext.request.contextPath }/resources/developerImage/reaven.gif" alt=""></a>
                        </div>
                        <div class="media-body">
                            <blockquote>NEVER EVER GIVE UP</blockquote>
                            <h3><a href="">- EDITOR ALICE</a></h3>
                        </div>
                    </div>   
                </div> 
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="contact-info bottom">
                    <h2>Contacts</h2>
                    <address>
                    E-mail :  <a href="mailto:someone@example.com">MOGAHEE@google.com</a> <br> 
                    Phone :  +8210 - ???? - ???? <br> 
                    Fax :  +822 - ???? - ???? <br> 
                    </address>

                    <h2>Address</h2>
                    <address>
                    대한민국 <br> 
                    서울 역삼동 <br> 
                    KH학원 <br> 
                    352CLASS <br> 
                    </address>
                </div>
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="contact-form bottom">
                    <h2>Send a message</h2>
                    <form id="main-contact-form" name="contact-form" method="post" action="sendemail.php">
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" required="required" placeholder="Name">
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" class="form-control" required="required" placeholder="Email Id">
                        </div>
                        <div class="form-group">
                            <textarea name="message" id="message" required="required" class="form-control" rows="8" placeholder="Your text here"></textarea>
                        </div>                        
                        <div class="form-group">
                            <input type="submit" name="submit" class="btn btn-submit" value="Submit">
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="copyright-text text-center">
                    <p>&copy; 352CLASS FINAL PROJECT TEAM MOHAGEE</p>
                    <p>Designed by <a target="_blank" href="http://www.themeum.com">Themeum</a></p>
                </div>
            </div>
        </div>
    </div>
</footer>