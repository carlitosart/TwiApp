package bo.ucbx.twiapp.bl;


import bo.ucbx.twiapp.dao.FollowsRepository;
import bo.ucbx.twiapp.dao.TweetsRepository;
import bo.ucbx.twiapp.dao.UsersRepository;
import bo.ucbx.twiapp.entities.FollowsEntity;
import bo.ucbx.twiapp.entities.TweetsEntity;
import bo.ucbx.twiapp.entities.UsersEntity;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TwitterBl {

    private UsersRepository usersRepository;
    private TweetsRepository tweetsRepository;
    private FollowsRepository followsRepository;
    private CacheManager cacheManager;

    public TwitterBl(UsersRepository usersRepository, TweetsRepository tweetsRepository, FollowsRepository followsRepository, CacheManager cacheManager) {
        this.usersRepository = usersRepository;
        this.tweetsRepository = tweetsRepository;
        this.followsRepository = followsRepository;
        this.cacheManager = cacheManager;
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
            // HIT CACHE
            List<UsersEntity> followers = followsRepository.findAllFollowersByUserId(userId);


            //CONNECTED USERS
            Cache cache = cacheManager.getCache("timeline");
            for (UsersEntity follower : followers){
                Cache.ValueWrapper value = cache.get(follower.getUserId());

                if (value!=null) {

                    System.out.println("Valor"+follower.getUserId()+"fue:"+value.get());

                    System.out.println("Tipo de Dato"+follower.getUserId()+"fue:"+value.get().getClass().getName());

                    List<TweetsEntity> tweetsEntities = (ArrayList<TweetsEntity>) value.get();
                    tweetsEntities.add(tweetsEntity);
                }
            }


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
