package com.testpro.feedBack;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.testpro.common.exception.BaseException;
import com.testpro.feedBack.bean.FeedBack;
import com.testpro.feedBack.bean.FeedBackSearch;
import com.testpro.feedBack.service.spi.IFeedBackService;

/**
 * FeedBack JUnit Test<br/>
 * 带事务处理,默认为回滚(MySQL的InnoDB引擎)
 * @author lubo
 * @date 2018-7-23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class FeedBackTest {
	@Autowired
	private IFeedBackService feedBackService;
	
	@Test
	public void find() {
		FeedBack s = feedBackService.find(FeedBack.class, 1);
		System.out.println( s );
	}
	
	@Test
	public void queryAll() {
		List<FeedBack> list = feedBackService.queryAll(FeedBack.class);
		System.out.println( list.size() );
	}
	
	@Test(expected = BaseException.class)
	//@Rollback(false)	//引擎:InnoDB
	public void add() {
		try {
			FeedBack entity = new FeedBack();
			entity.setId(1);
			feedBackService.save(entity);
			System.out.println( entity );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		FeedBack entity = new FeedBack();
		entity.setId(1);
		feedBackService.update(entity);
	}
	
	@Test
	public void page() {
		FeedBackSearch search = new FeedBackSearch();
		// search.setPageSize(5);
		search.setPageNo(1);
		// search.setName("s");
		List<FeedBack> list = feedBackService.page(FeedBack.class, search);
		System.out.println(
				"第:"+search.getPageNo()+"页, " +
				"总页数:"+search.getTotalPages()+", " +
				"总记录:"+search.getTotalRecords());
		for (FeedBack s : list) {
			System.out.println(s);
		}
	}
	
	@Test 
	public void delete() {
		feedBackService.delete(FeedBack.class, 1);
		// feedBackService.deleteByIds(FeedBack.class, new Object[]{3,5,9});
	}
	
}