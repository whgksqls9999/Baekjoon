WITH SALES_DATA AS (SELECT
                        *
                    FROM
                        BOOK_SALES
                    WHERE
                        DATE_FORMAT(SALES_DATE, '%Y-%m') = '2022-01')

SELECT
    A.AUTHOR_ID,
    A.AUTHOR_NAME,
    B.CATEGORY,
    SUM(PRICE * SALES) AS TOTAL_SALES
FROM
    AUTHOR A,
    BOOK B,
    SALES_DATA
WHERE
    SALES_DATA.BOOK_ID = B.BOOK_ID AND B.AUTHOR_ID = A.AUTHOR_ID
GROUP BY
    AUTHOR_ID,
    CATEGORY
ORDER BY
    A.AUTHOR_ID,
    B.CATEGORY DESC
    