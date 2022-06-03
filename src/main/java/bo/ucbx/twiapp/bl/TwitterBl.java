package bo.ucbx.twiapp.bl;


import bo.ucbx.twiapp.dao.FollowsRepository;
import bo.ucbx.twiapp.dao.TweetsRepository;
import bo.ucbx.twiapp.dao.UsersRepository;
import bo.ucbx.twiapp.entities.FollowsEntity;
import bo.ucbx.twiapp.entities.TweetsEntity;
import bo.ucbx.twiapp.entities.UsersEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TwitterBl {

    private UsersRepository usersRepository;
    private TweetsRepository tweetsRepository;
    private FollowsRepository followsRepository;

    public TwitterBl(UsersRepository usersRepository, TweetsRepository tweetsRepository, FollowsRepository followsRepository) {
        this.usersRepository = usersRepository;
        this.tweetsRepository = tweetsRepository;
        this.followsRepository = followsRepository;
    }

    public void createUser(UsersEntity usersEntity){
        this.usersRepository.save(usersEntity);
    }

    public void writeTweet(int userId,String text){
        Optional<UsersEntity> usersEntityOptional = this.usersRepository.findById(userId);
        if (usersEntityOptional.isPresent()){
            UsersEntity usersEntity = usersEntityOptional.get();
            TweetsEntity tweetsEntity = new TweetsEntity();
            tweetsEntity.setUserId(userId);
            tweetsEntity.setTweetText(text);
            tweetsRepository.save(tweetsEntity);
        }else {
            throw new RuntimeException("No existe usuario"+userId);
        }
    }

    public void follow(FollowsEntity followsEntity){
        this.followsRepository.save(followsEntity);
    }


    public List<TweetsEntity> getTimeline(Integer userId) {
        return tweetsRepository.findTweetsForFollowers(userId);
    }
}
