package org.zerock.partThree.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.thymeleaf.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.QBoard;
import org.zerock.partThree.entity.QMember;
import org.zerock.partThree.entity.QReply;

import java.util.List;
import java.util.stream.Collectors;

import static org.zerock.partThree.entity.QBoard.board;
import static org.zerock.partThree.entity.QMember.member;
import static org.zerock.partThree.entity.QReply.reply;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

public SearchBoardRepositoryImpl(){
    super(Board.class);
}

    @Override
    public Board search1() {

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.member.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());

        tuple.groupBy(board);

        log.info("================================");
        log.info(tuple);
        log.info("================================");

        List<Tuple> result = tuple.fetch();
        result.forEach(i->log.info(i));
        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("SearchPage.......");

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.member.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);

        booleanBuilder.and(expression);

        if(type != null){
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String s : typeArr){
                switch (s){
                    case "t" :
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "c" :
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                    case "w" :
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        //tuple.order by(board.bno.desc());
        sort.stream().forEach(order ->{
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            log.info("prop : "+prop);

            PathBuilder orderByExpression = new PathBuilder(Board.class,"board");
            tuple.orderBy(new OrderSpecifier(direction,orderByExpression.get(prop)));
        });


        tuple.groupBy(board);

        //page처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();
        result.forEach(i->log.info(i));

        long count = tuple.fetchCount();
        log.info("Count : "+count);

        return new PageImpl<Object[]>(result.stream().map(t ->t.toArray()).collect(Collectors.toList()),pageable,count);
    }
}
