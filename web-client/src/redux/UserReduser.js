const SET_ERROR="SET_ERROR"
const SET_STRUCTURED_ERROR="SET_STRUCTURED_ERROR"
const SET_PAGE="SET_PAGE"

export let initialState = {
    errors:[],
    structureErrors:[],
    page: {
        number: null,
        size: null,
        total_pages: null,
        total_elements: null,
        first: null,
        number_of_elements: null,
        last: null,
        content: null
    }
}

const userReduser=(state=initialState,action)=>{
    let state_copy
    switch (action.type) {
        case SET_ERROR:
            state_copy = {
                ...state,
                errors: action.errors,
            }
            return state_copy;
        case SET_STRUCTURED_ERROR:
            state_copy = {
                ...state,
                structureErrors: action.errors,
            }
            return state_copy;
        case SET_PAGE:
            state_copy = {
                ...state,
                page: action.page,
            }
            return state_copy;
        default:
            return state;
    }
}
export const setError=(errors)=>
    ({type:SET_ERROR,errors:errors})
export const setStructuredError=(errors)=>
    ({type:SET_STRUCTURED_ERROR,errors:errors})
export const setPage=(page)=>
    ({type:SET_PAGE,page:page})

export default userReduser