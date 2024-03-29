import { deletePost, memberDelete } from "api";
import { useNavigate } from "react-router";
import { useSetRecoilState } from "recoil";
import {
  isDeleteModalState,
  isPostDeleteModalState,
} from "../../components/atom";
import { motion } from "framer-motion";
const LayoutVariant = {
  hidden: {
    opacity: 0,
    // backGroundColor: "rgba(0,0,0,0.5)",
  },
  showing: {
    opacity: 1,
  },
  exit: {
    opacity: 0,
  },
};

function PostDeleteModal({ postId }: any) {
  const navigate = useNavigate();

  const setIsPostDeleteModal = useSetRecoilState(isPostDeleteModalState);

  const onClick = async (event: any) => {
    if (event.currentTarget.id === "yes") {
      try {
        await deletePost(postId)?.then((data) => console.log(data));

        alert("모집글이 삭제되었습니다.");
        navigate("/post");
        setIsPostDeleteModal(false);
      } catch (error) {
        alert("알수 없는 오류입니다.");
      }

      // window.location.replace("/post");
    } else if (event.currentTarget.id === "no") {
      setIsPostDeleteModal(false);
    }
  };

  return (
    <div>
      <motion.div
        onClick={onClick}
        variants={LayoutVariant}
        initial="hidden"
        animate="showing"
        exit="exit"
        id="no"
        className="z-10 bg-[rgba(0,0,0,0.5)] fixed top-0 left-0 w-full h-screen opacity-100"
      ></motion.div>
      <div className="modal fixed z-20 flex justify-center w-full">
        <div className="w-4/5 rounded-2xl md:w-[1000px] md:rounded-3xl bg-[white] h-[330px] py-[30px] px-[30px] flex flex-col justify-between">
          <span className="flex justify-end">
            <i
              onClick={onClick}
              id="no"
              className="fa-solid fa-xmark text-[30px]"
            ></i>
          </span>
          <span className="flex justify-center">
            <span className="flex flex-col w-[300px] h-[100px] items-center">
              <img className="w-[150px]" src="/img/logo_hisbee.png " />
              <p className="font-semibold text-[20px] md:text-[25px] mt-[10px] md:mt-[10px]">
                정말로 삭제하시겠습니까?
              </p>
            </span>
          </span>

          <div className="flex justify-center ">
            <span className="w-[260px] h-[50px] flex justify-between">
              <button
                onClick={onClick}
                id="no"
                className="w-[120px] h-[35px] border border-gray-300 rounded-full text-[20px] font-medium shadow-sm"
              >
                {" "}
                안할래요 !
              </button>
              <button
                onClick={onClick}
                id="yes"
                className="w-[120px] h-[35px] border border-gray-300 rounded-full text-[20px] font-medium shadow-sm"
              >
                삭제하기
              </button>
            </span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostDeleteModal;
