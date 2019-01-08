package controller.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SearchUtil {
    private final static Logger logger = Logger.getLogger(SearchUtil.class);
    private final static String BOOK_NAME_ATRIBUTE ="book-name";
    private final static String TAGS_NAME_ATRIBUTE ="tags";
    private final static String AUTHOR_NAME_ATRIBUTE ="author";
    private final static String BOOK_NAME_FIELD =" book_name ";
    private final static String TAGS_NAME_FIELD =" book_name ";//
    private final static String AUTHOR_NAME_FIELD =" book_name ";//

    private final static String WHERE_STATEMENT =" where ";
    private final static String LIKE =" like ";
    private final static String AND =" and ";

    public boolean checkNotNullSearchAtributes(HttpServletRequest request){
        return request.getParameter(BOOK_NAME_ATRIBUTE)==null || request.getParameter(BOOK_NAME_ATRIBUTE).isEmpty() ||
                request.getParameter(TAGS_NAME_ATRIBUTE)!=null || request.getParameter(TAGS_NAME_ATRIBUTE).isEmpty() ||
                request.getParameter(AUTHOR_NAME_ATRIBUTE)!=null ||request.getParameter(AUTHOR_NAME_ATRIBUTE).isEmpty();
    }
    public String addSearchStatement(HttpServletRequest request,String query){
        query+=(query.contains(WHERE_STATEMENT.trim()))?AND:WHERE_STATEMENT;
        query+=(request.getParameter(BOOK_NAME_ATRIBUTE)!=null&& !request.getParameter(BOOK_NAME_ATRIBUTE).isEmpty())?
                (BOOK_NAME_FIELD+LIKE+"'%"+request.getParameter(BOOK_NAME_ATRIBUTE)+"%'"+AND)
                :
                "";
        query+=(request.getParameter(TAGS_NAME_ATRIBUTE)!=null&& !request.getParameter(TAGS_NAME_ATRIBUTE).isEmpty())?
                (TAGS_NAME_FIELD+LIKE+"'%"+request.getParameter(TAGS_NAME_ATRIBUTE)+"%'"+AND)
                :
                "";
        query+=(request.getParameter(AUTHOR_NAME_ATRIBUTE)!=null&& !request.getParameter(AUTHOR_NAME_ATRIBUTE).isEmpty())?//todo disable this
                (AUTHOR_NAME_FIELD+LIKE+"'%"+request.getParameter(AUTHOR_NAME_ATRIBUTE)+"%'"+AND)
                :
                "";
        return query.substring(0, query.length() - 4);
    }
}
