import {gql} from "apollo-boost";

export const GET_ALL_TABLE = gql`
    {
        users{
            id
            createdDate
            updatedDate
            email
        }
    }
`;
